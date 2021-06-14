#!/usr/bin/python3

limit = 493

import sqlite3, requests, json

def get(url, base = "https://pokeapi.co/api/v2/"): return json.loads(requests.get(base + url).text)
def pretty_name(names, lang = "fr"): return (list(filter(lambda x: x["language"]["name"] == lang, names)) + [{"name": False}])[0]["name"]
def url_id(url): return int(url.split("/")[-2])
def name_id(name, table): return ([i["id"] for i in table.values() if pretty_name(i["names"], "en") == name] + [False])[0]

db = sqlite3.connect("test.db")
cur = db.cursor()

cur.execute("CREATE TABLE pokemon (id int, name text, pretty_name text, base_experience int, height int, sprite_back text, sprite_front text, weight int, PRIMARY KEY(id));")
cur.execute("CREATE TABLE pokemon_stat (pokemon int, stat int, base_stat int, effort int, PRIMARY KEY(pokemon, stat));")
cur.execute("CREATE TABLE pokemon_ability (pokemon int, ability int, PRIMARY KEY(pokemon, ability));")
cur.execute("CREATE TABLE pokemon_move (pokemon int, move int, PRIMARY KEY(pokemon, move));")
cur.execute("CREATE TABLE pokemon_type (pokemon int, type text, PRIMARY KEY(pokemon, type));")
cur.execute("CREATE TABLE stat (id int, name text, pretty_name text, PRIMARY KEY(id));")
cur.execute("CREATE TABLE ability (id int, name text, pretty_name text, PRIMARY KEY(id));")
cur.execute("CREATE TABLE move (id int, name text, pretty_name text, accuracy int, damage_class text, crit_rate int, power int, pp int, priority int, type text, PRIMARY KEY(id));")
cur.execute("CREATE TABLE sets (id int, name text, tier text, pokemon int, ability int, nature text, PRIMARY KEY(id));")
cur.execute("CREATE TABLE sets_stat (id int, stat int, effort int, PRIMARY KEY(id, stat));")
cur.execute("CREATE TABLE sets_move (id int, move int, PRIMARY KEY(id, move));")

#pokemon = {i: get(f"pokemon/{i}") for i in range(1, limit + 1)}
pokemon = json.load(open("pokemon.json"))
pokemon_species = json.load(open("pokemon_species.json"))
for i in pokemon.values(): cur.execute("INSERT INTO pokemon VALUES(?, ?, ?, ?, ?, ?, ?, ?);", (i["id"], i["name"], pretty_name(pokemon_species[str(i["id"])]["names"]), i["base_experience"], i["height"], i["sprites"]["back_default"], i["sprites"]["front_default"], i["weight"]))

stat = json.load(open("stat.json"))
for i in pokemon.values():
    for j in i["stats"]: cur.execute("INSERT INTO pokemon_stat VALUES(?, ?, ?, ?);", (i["id"], url_id(j["stat"]["url"]), j["base_stat"], j["effort"]))

ability = json.load(open("ability.json"))
for i in pokemon.values():
    for j in i["abilities"]: cur.execute("INSERT INTO pokemon_ability VALUES(?, ?);", (i["id"], url_id(j["ability"]["url"])))

move = json.load(open("move.json"))
for i in pokemon.values():
#    for j in [k for k in i["moves"] if not any([k["version_group_details"][l]["level_learned_at"] for l in range(len(k["version_group_details"]))]) and pretty_name(move[str(url_id(k["move"]["url"]))]["names"])]: cur.execute("INSERT INTO pokemon_move VALUES(?, ?);", (i["id"], url_id(j["move"]["url"])))
    for j in list(filter(lambda x: pretty_name(move[str(url_id(x["move"]["url"]))]["names"]), i["moves"])): cur.execute("INSERT INTO pokemon_move VALUES(?, ?);", (i["id"], url_id(j["move"]["url"])))

for i in pokemon.values():
    for j in i["types"]: cur.execute("INSERT INTO pokemon_type VALUES(?, ?);", (i["id"], j["type"]["name"]))

for i in stat.values(): cur.execute("INSERT INTO stat VALUES(?, ?, ?);", (i["id"], i["name"], pretty_name(i["names"])))

for i in ability.values(): cur.execute("INSERT INTO ability VALUES(?, ?, ?);", (i["id"], i["name"], pretty_name(i["names"])))

for i in list(filter(lambda x: any([url_id(j["url"]) in range(1, limit + 1) for j in x["learned_by_pokemon"]]) and pretty_name(x["names"]), move.values())): cur.execute("INSERT INTO move VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?);", (i["id"], i["name"], pretty_name(i["names"]), i["accuracy"], i["damage_class"]["name"], i["meta"]["crit_rate"], i["power"], i["pp"], i["priority"], i["type"]["name"]))

sets = json.load(open("gen4.json"))
stat_dict = {"hp": 1, "atk": 2, "def": 3, "spa": 4, "spd": 5, "spe": 6}
for k, v in {k: v["dex"] for k, v in sets.items() if len(v) > 1}.items():
    for l, w in v.items():
        for m, x in w.items():
            sets_names = (name_id(l, pokemon_species), name_id(x["ability"], ability), [name_id(i, move) for i in x["moves"]])
            if all(sets_names[:2]) and all(sets_names[2]):
                sets_id = abs(hash(str((k, l, m, x))))
                cur.execute("INSERT INTO sets VALUES(?, ?, ?, ?, ?, ?);", (sets_id, m, k, sets_names[0], sets_names[1], x["nature"].upper()))
                for n, y in x["evs"].items(): cur.execute("INSERT INTO sets_stat VALUES(?, ?, ?);", (sets_id, stat_dict[n], y))
                for i in sets_names[2]: cur.execute("INSERT INTO sets_move VALUES(?, ?);", (sets_id, i))

db.commit()
db.close()
