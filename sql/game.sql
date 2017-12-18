CREATE DATABASE game;
USE game;

				#ENTITIES

CREATE TABLE accounts (id INT AUTO_INCREMENT, 
                    id_avatar INT,
                    email VARCHAR(50), 
                    login VARCHAR(30), 
                    password VARCHAR(10), 
                    PRIMARY KEY(id),
                    FOREIGN KEY(id_avatar) REFERENCES avatars(id));
CREATE TABLE avatars (id INT AUTO_INCREMENT,
                    id_armor INT,
                    id_weapon INT,
                    id_class INT,
                    name VARCHAR(30) UNIQUE,
                    gender VARCHAR(10),
                    level INT, 
                    EXP INT, 
                    money INT, 
                    HP INT, 
                    MP INT, 
                    STR INT, 
                    DEF INT, 
                    spell_power INT,
                    magic_resistance DEC, 
                    hit_chance DEC, 
                    evade_chance DEC, 
                    crit_chance DEC, 
                    multiplier_crit DEC,
                    PRIMARY KEY(id),
                    FOREIGN KEY(id_weapon) REFERENCES weapons(id),
                    FOREIGN KEY(id_class) REFERENCES classes(id),
                    FOREIGN KEY(id_armor) REFERENCES armors(id));
CREATE TABLE NPCs (id INT AUTO_INCREMENT,
                    id_type INT,
                    name VARCHAR(30) UNIQUE,
                    money INT,
                    EXP INT, 
                    PRIMARY KEY(id),
                    FOREIGN KEY(id_type) REFERENCES types_of_NPCs(id));
CREATE TABLE armors (id INT AUTO_INCREMENT,
                    name VARCHAR(30),
                    DEF INT,
                    magic_resistance DEC, 
                    PRIMARY KEY(id));
CREATE TABLE weapons (id INT AUTO_INCREMENT,
                    name VARCHAR(30),
                    ATK INT, 
                    PRIMARY KEY(id));
CREATE TABLE spells (id INT AUTO_INCREMENT,
                    name VARCHAR(30), 
                    power DEC,
                    cost INT,
                    PRIMARY KEY(id));
CREATE TABLE items (id INT AUTO_INCREMENT,
                    name VARCHAR(30), 
                    cost INT, 
                    PRIMARY KEY(id));
# CREATE TABLE quests (id INT AUTO_INCREMENT,
# 					id_reward INT,
#                     name VARCHAR(30),
# 					first_step INT,
#                     description TEXT,
#                     PRIMARY KEY(id),
# 					FOREIGN KEY(first_step) REFERENCES steps(id),
# 					FOREIGN KEY(id_reward) REFERENCES rewards(id));
# CREATE TABLE steps (id INT AUTO_INCREMENT,
# 					name VARCHAR(30),
# 					PRIMARY KEY(id));
# CREATE TABLE variants (id INT AUTO_INCREMENT,
#                     step_id INT,
#                     leads_to_id INT,
#                     PRIMARY KEY(id),
#                     FOREIGN KEY(step_id) REFERENCES steps(id),
#                     FOREIGN KEY(leads_to_id) REFERENCES steps(id));
# CREATE TABLE rewards (id INT AUTO_INCREMENT,
#                     EXP INT,
#                     money INT,
#                     id_item INT,
#                     PRIMARY KEY(id),
#                     FOREIGN KEY(id_item) REFERENCES items(id));
CREATE TABLE classes (id INT AUTO_INCREMENT, 
                    name VARCHAR(30) UNIQUE,
                    HP_multiplier DEC, 
                    MP_multiplier DEC, 
                    STR_multiplier DEC, 
                    DEF_multiplier DEC,
                    spell_power_multiplier DEC,
                    PRIMARY KEY(id));
CREATE TABLE types_of_NPCs (id INT AUTO_INCREMENT, 
                    name VARCHAR(30), 
                    HP_multiplier DEC, 
                    MP_multiplier DEC, 
                    ATK_multiplier DEC, 
                    DEF_multiplier DEC, 
                    spell_power_multiplier DEC,
                    magic_resistance DEC, 
                    hit_chance DEC,
                    evade_chance DEC, 
                    crit_chance DEC, 
                    crit_multiplier DEC, 
                    PRIMARY KEY(id));
					
				#CONNECTORS

CREATE TABLE completed_quests (id_avatar INT, id_step INT, id_quest INT, PRIMARY KEY(id_avatar, id_step, id_quest),
					FOREIGN KEY(id_avatar) REFERENCES avatars(id), 
                    FOREIGN KEY(id_step) REFERENCES steps(id),
                    FOREIGN KEY(id_quest) REFERENCES quests(id));
CREATE TABLE inventories (id_avatar INT, id_item INT, PRIMARY KEY(id_avatar, id_item), 
                    FOREIGN KEY(id_avatar) REFERENCES avatars(id), 
                    FOREIGN KEY(id_item) REFERENCES items(id));
CREATE TABLE loot (id_NPC INT, id_item INT, PRIMARY KEY(id_NPC, id_item), 
                    FOREIGN KEY(id_NPC) REFERENCES NPCs(id), 
                    FOREIGN KEY(id_item) REFERENCES items(id));
CREATE TABLE avatar_spells (id_avatar INT, id_spell INT, PRIMARY KEY(id_avatar, id_spell), 
                    FOREIGN KEY(id_avatar) REFERENCES avatars(id), 
                    FOREIGN KEY(id_spell) REFERENCES spells(id));
CREATE TABLE NPC_spells (id_NPC INT, id_spell INT, PRIMARY KEY(id_NPC, id_spell), 
                    FOREIGN KEY(id_NPC) REFERENCES NPCs(id), 
                    FOREIGN KEY(id_spell) REFERENCES spells(id));
#
# DROP DATABASE game;