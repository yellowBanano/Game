CREATE DATABASE game;
USE game;

				#ENTITIES

CREATE TABLE accounts (id INT AUTO_INCREMENT, 
					email VARCHAR(50), 
                    login VARCHAR(30), 
                    password VARCHAR(10), 
                    PRIMARY KEY(id));
CREATE TABLE avatars (id INT AUTO_INCREMENT, 
					name VARCHAR(30) UNIQUE, 
                    gender VARCHAR(1),
                    level INT, 
                    EXP INT, 
                    money INT, 
                    HP INT, 
                    MP INT, 
                    STR INT, 
                    DEF INT, 
                    speed INT, 
                    spell_power INT, 
                    magic_resistance DEC, 
                    hit_chance DEC, 
                    evade_chance DEC, 
                    crit_chance DEC, 
                    multiplier_crit DEC,
                    PRIMARY KEY(id));
CREATE TABLE NPCs (id INT AUTO_INCREMENT, 
					id_type INT,
					name VARCHAR(30) UNIQUE,
                    money INT,
                    EXP INT, 
                    PRIMARY KEY(id),
                    FOREIGN KEY(id_type) REFERENCES types_of_NPCs(id));
CREATE TABLE armors (id INT AUTO_INCREMENT,
					id_avatar INT,
                    id_type INT,
                    name VARCHAR(30), 
                    DEF INT,
                    magic_resistance DEC, 
                    PRIMARY KEY(id), 
                    FOREIGN KEY(id_avatar) REFERENCES avatars(id),
                    FOREIGN KEY(id_type) REFERENCES types_of_armors(id));
CREATE TABLE weapons (id INT AUTO_INCREMENT, 
					id_avatar INT, 
                    id_type INT, name VARCHAR(30),
                    ATK INT, 
                    PRIMARY KEY(id), 
                    FOREIGN KEY(id_avatar) REFERENCES avatars(id), 
                    FOREIGN KEY(id_type) REFERENCES types_of_weapons(id));
CREATE TABLE spells (id INT AUTO_INCREMENT,
					name VARCHAR(30), 
                    id_type INT,
                    power DEC, 
                    PRIMARY KEY(id),
                    FOREIGN KEY(id_type) REFERENCES types_of_spells(id));
CREATE TABLE items (id INT AUTO_INCREMENT, 
					name VARCHAR(30), 
                    cost INT, 
                    amount INT, 
                    PRIMARY KEY(id));
CREATE TABLE quests (id INT AUTO_INCREMENT, 
					name VARCHAR(30), 
                    description TEXT, 
                    PRIMARY KEY(id));
CREATE TABLE rewards (id INT AUTO_INCREMENT,
					EXP INT, 
                    money INT, 
                    id_item INT, 
                    PRIMARY KEY(id), 
                    FOREIGN KEY(id_item) REFERENCES items(id));
CREATE TABLE races (id INT AUTO_INCREMENT, 
					id_avatar INT, 
                    name VARCHAR(30) UNIQUE, 
                    PRIMARY KEY(id), 
                    FOREIGN KEY(id_avatar) REFERENCES avatars(id));
CREATE TABLE classes (id INT AUTO_INCREMENT, 
					id_avatar INT, 
                    name VARCHAR(30) UNIQUE,
                    HP_multiplier DEC, 
                    MP_multiplier DEC, 
                    STR_multiplier DEC, 
                    DEF_multiplier DEC,
                    spell_power_multiplier DEC,
                    PRIMARY KEY(id), 
                    FOREIGN KEY(id_avatar) REFERENCES avatars(id));
CREATE TABLE types_of_NPCs (id INT AUTO_INCREMENT, 
					name VARCHAR(30), 
                    HP_multiplier DEC, 
                    MP_multiplier DEC, 
                    ATK_multiplier DEC, 
                    DEF_multiplier DEC, 
                    speed INT, 
                    spell_power_multiplier DEC,
                    magic_resistance DEC, 
                    hit_chance DEC,
                    evade_chance DEC, 
                    crit_chance DEC, 
                    crit_multiplier DEC, 
                    PRIMARY KEY(id));
CREATE TABLE types_of_armors (id INT AUTO_INCREMENT, 
					name VARCHAR(30) UNIQUE, 
                    speed_reduce INT, 
                    PRIMARY KEY(id));
CREATE TABLE types_of_weapons (id INT AUTO_INCREMENT, 
					name VARCHAR(30) UNIQUE, 
                    attack_speed DEC,
                    PRIMARY KEY(id));
CREATE TABLE types_of_spells (id INT AUTO_INCREMENT, 
					id_spell INT, 
                    target VARCHAR(30), 
                    area INT, 
                    range_cast INT, 
                    PRIMARY KEY(id));
					
				#CONNECTORS
                
CREATE TABLE inventories (id_avatar INT, id_item INT, PRIMARY KEY(id_avatar, id_item), 
                    FOREIGN KEY(id_avatar) REFERENCES avatars(id), 
                    FOREIGN KEY(id_item) REFERENCES items(id));
CREATE TABLE quest_reward (id_quest INT, id_reward INT, PRIMARY KEY(id_quest, id_reward), 
                    FOREIGN KEY(id_quest) REFERENCES quests(id), 
                    FOREIGN KEY(id_reward) REFERENCES rewards(id));
CREATE TABLE loot (id_NPC INT, id_item INT, PRIMARY KEY(id_NPC, id_item), 
					FOREIGN KEY(id_NPC) REFERENCES NPCs(id), 
                    FOREIGN KEY(id_item) REFERENCES items(id));
CREATE TABLE avatar_spells (id_avatar INT, id_spell INT, PRIMARY KEY(id_avatar, id_spell), 
					FOREIGN KEY(id_avatar) REFERENCES avatars(id), 
                    FOREIGN KEY(id_spell) REFERENCES spells(id));
CREATE TABLE NPC_spells (id_NPC INT, id_spell INT, PRIMARY KEY(id_NPC, id_spell), 
					FOREIGN KEY(id_NPC) REFERENCES NPCs(id), 
                    FOREIGN KEY(id_spell) REFERENCES spells(id));

DROP DATABASE game;









