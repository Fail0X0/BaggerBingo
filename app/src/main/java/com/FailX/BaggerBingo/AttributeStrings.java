package com.FailX.BaggerBingo;

import java.util.Arrays;
import java.util.stream.Stream;

public final class AttributeStrings {

    private String[] punishment;
    private String[] haircolor;
    private String[] clothes;
    private String[] accessories;
    private String[] height;
    private String[] flirt;
    private String[] haircolor_de = new String[]{
            "Blond",
            "Braun",
            "Rot",
            "Schwarz",
            "Gefärbt",
            "Joker"
    };
    private String[] clothes_de = new String[]{
            "Casual",
            "Modern",
            "Surfer Look",
            "Emo",
            "Extravagant",
            "Hippie",
            "Hip Hop",
            "Hipster",
            "Punk",
            "Sportmode",
            "Joker"
    };
    private String[] accessories_de = new String[]{
            "Cappy",
            "Tattoos",
            "Uhr",
            "Handy",
            "Kette",
            "Armband",
            "Ohrringe",
            "Joker"
    };
    private String[] height_de = new String[]{
            "Groß",
            "Durchschnittlich",
            "Klein",
            "Joker"
    };
    private String[] flirt_level1_de = new String[]{
            "Hast du Lust, mir ein Getränk auszugeben?",
            "Studierst du auch hier?",
            "Im Lexikon ist unter »Wow« ein Bild von dir aufgeführt.",
            "Hey, was machst du für eine Sportart?",
            "Wahrheit oder Pflicht?",
            "Schon mal was von Bagger Bingo gehört?",
            "Willst du meine Tattoos sehen?",
            "Weißt du, wie spät es ist?",
            "Warst du beim Friseur?",
            "Hey Lisa wie geht’s?",
            "Hast du schon mal was gewonnen?\nNein = Ich aber deine Aufmerksamkeit.\nJa = Richtig, meine Aufmerksamkeit.",
            "Ab heute glaub ich an den Sandmann. Du bist ein Traum.",
            "Nice to meet you. Do you speak german?",
            "Hättest du Lust an einer Umfrage teilzunehmen?\nNein = Ok, denn ich mache auch keine.\nJa = Auf einer Skala von 10 bis 10, wie gern würdest du mit mir ausgehen?",
            "Hey, bekomme ich deine Nummer?",
            "Spontan 3 Dinge, die an mir gut aussehen!",
            "Wissenschaftler haben herausgefunden, sind dann aber wieder rein gegangen.",
            "Ist dein Vater ein Dieb?\nIch glaube, er hat Sterne gestohlen und dir in die Augen gelegt.",
            "Ist dein Vater Terrorist?\nDenn du siehst echt Bombe aus!",
            "Ich stehe zwar nicht auf scharf, aber bei dir mache ich eine Ausnahme.",
            "Darf ich dir mich vorstellen?",
            "Anmachen ist nichts für mich.\nSag du mir, wie du angesprochen werden möchtest",
            "Ich stehe heute unter Strom.\nPassenderweise spüre ich einen Draht zwischen uns",
            "Lust zu tanzen?",
            "Deine Augen verraten mir vieles, nur deinen Namen nicht.",
            "Wenn du eine Kartoffel wärst, wärst du eine Süßkartoffel.",
            "All diese Kurven und ich ohne Bremsen.",
            "Du siehst aus, als hättest du das dringende Bedürfnis mir deinen Namen zu sagen.",
    };
    private String[] flirt_level2_de = new String[]{
            "Wenn ich nicht Trinken würde, wärst du bestimmt auch hübsch.",
            "Hast du dich schon mal beim feiern verknallt?\nNein = Jetzt schon.\nJa = wollen wir dann zusammen sein?",
            "Schöne Lippen.\nKönnen die auch küssen?",
            "Hey, was willst du morgen zum Frühstück am Bett?",
            "Ich bin neu in der Stadt.\nKannst du mir den Weg zu dir nach Hause zeigen?",
            "Küssen ist gesund und verlängert das Leben.\nKomm her und ich mach dich unsterblich.",
            "Du bist ein böses Mädchen!\nGeh sofort auf mein Zimmer!",
            "Mein Cousin ist Schnapsbrenner.\nKomm, und ich zeig dir mal, wie man ganz einfach Rum macht.",
    };
    private String[] flirt_level3_de = new String[]{
            "Hey, darf ich dich verschnüren wie ein Paket?",
            "Lust auf 'ne Zigarette danach?",
            "Hast du Bock auf guten Sex?\nNein = Dann bist du bei mir genau richtig.\nJa = (Zieh durch!)",
            "Fick mich wenn ich falsch liege, aber findest du mich heiß?",
            "Oh man siehst du zerknittert aus.\nSoll ich dich mal bügeln?",
            "Sag mal schmeckt deine Zunge genauso süß, wie du aussiehst?",
            "Ich bin gut drauf, bist du gut drunter?",
            "Meine Liebe zu dir ist wie Durchfall,\nich kann sie einfach nicht zurück halten.",
            "Ich habe mein Pferd verloren, darf ich auf dir reiten?",
    };
    private String[] punishment_de = new String[]{
            "Handstand",
            "Jemandem einen ausgeben",
            "Sich entschuldigen",
            ">>Bagger Bingo Fail<< schreien",
            "Die Person neben der Angesprochenen anbaggern",
            "Bescheuert tanzen",
            "3 Pirouetten drehen",
            "Bescheuert anfangen zu lachen",
            "Ein peinliches Selfi mit einem Freund machen",
            "Hol dir einen Fist bump von einer unbekannten Person",
            "Spiel Schere, Stein, Papier mit einer unbekannten Person",
    };
    private String[] haircolor_en = new String[]{
            "Blond",
            "Brown",
            "Red",
            "Black",
            "Dyed",
            "Joker"
    };
    private String[] clothes_en = new String[]{
            "Casual",
            "Modern",
            "Surfer Look",
            "Emo",
            "Extravagant",
            "Hippie",
            "Hip Hop",
            "Hipster",
            "Punk",
            "Sporty",
            "Joker"
    };
    private String[] accessories_en = new String[]{
            "Cappy",
            "Tattoos",
            "Watch",
            "Phone",
            "Necklace",
            "Bracelet",
            "Earrings",
            "Joker"
    };
    private String[] height_en = new String[]{
            "Tall",
            "Average",
            "Short",
            "Joker"
    };
    private String[] flirt_level1_en = new String[]{
            "Do you want to buy me a drink?",
            "Are you studying here as well?",
            "A picture of you is listed in the dictionary under >>Wow<<",
            "Hey, what are you doing for sports?",
            "Truth or dare?",
            "Did you ever heard of Flirt Bingo?",
            "You want to see my tattoos?",
            "Do you know how late it is?",
            "Did you get a Haircut?",
            "Hey Lisa, how you doing?",
            "Did you ever won something?/\nNo = Well, now you won my attention.\nYes = That's right, my attention.",
            "From today on, I believe in the Sandman, because you're a dream",
            "Nice to meet you. Do you speak German?",
            "Are you down for a short survey?\nNo = Coincidence, because I don't do any.\nYes = From a scale from 10 to 10, how likely would you date me?",
            "Hey can I get your number?",
            "Quick! 3 things that you like on me!",
            "Scientists found out, but then went back inside.",
            "Is your dad a thief?\nI think he stole two stars and laid them in your eyes.",
            "Is your dad a terrorist?\nYou look like a bomb!",
            "May I introduce me to you?",
            "Flirting is nothing for me.\nTell me how I should approach you.",
            "I'm electrified today.\nIt is a coincidence that I feel a connection between us two.",
            "Would you like to dance?",
            "Your eyes tell me a lot, just not your name.",
            "If you were a potato, you would be a sweet potato.",
            "All these curves and me without brakes.",
            "You look like you have an urgent need to tell me your name.",
    };
    private String[] flirt_level2_en = new String[]{
            "I bet my ass, if I wouldn't drink, you would be beautiful as well.",
            "Did you ever fell in love while partying?\nNo = Well, now you did.\nYes = do we want to be together then?",
            "Nice lips, do they kiss as well?",
            "Hey, what do you want for breakfast in bed tomorrow?",
            "I'm new in town.\nCan you show me the way to your home?",
            "Kissing is healthy and extends life.\nCome here and I'll make you immortal.",
            "You are a bad girl!\nGo to my room immediately!",
    };
    private String[] flirt_level3_en = new String[]{
            "Hey, can I tie you up like a package?",
            "You want a cigarette after?",
            "Are you down for good sex?\nNo = Then you are damn right with me.\nYes = (Go for it!)",
            "Fuck me if I'm wrong, but do you think I'm hot?",
            "Oh you look crumpled\nShould I iron you?",
            "Tell me, does your tongue taste as sweet as you look?",
            "You make me high, are you good below?",
            "My love for you is like diarrhea, I just can't hold it back.",
            "I lost my horse, can I ride on you?",
    };
    private String[] punishment_en = new String[]{
            "Handstand",
            "Pay one drink for a friend",
            "Apologize",
            "Scream \"Flirt Bingo fail\"",
            "Flirt with the person next to the target",
            "Dancing funny",
            "Do 3 pirouettes",
            "Laugh stupid",
            "Take a silly selfi with a friend",
            "Get a fist bump from a stranger",
            "Play rock, paper, scissor with a stranger",
    };
    public AttributeStrings(String language, int level) {
        if (language.matches("en")) {
            punishment = punishment_en;
            haircolor = haircolor_en;
            clothes = clothes_en;
            accessories = accessories_en;
            height = height_en;
            switch (level) {
                case 1: {
                    flirt = flirt_level1_en;
                    break;
                }
                case 2: {
                    flirt = Stream.concat(
                            Arrays.stream(flirt_level1_en),
                            Arrays.stream(flirt_level2_en)
                    ).toArray(String[]::new);
                    break;
                }
                default: {
                    flirt = Stream.concat(
                            Arrays.stream(flirt_level1_en),
                            Arrays.stream(flirt_level2_en)
                    ).toArray(String[]::new);
                    flirt = Stream.concat(
                            Arrays.stream(flirt),
                            Arrays.stream(flirt_level3_en)
                    ).toArray(String[]::new);
                    break;
                }
            }
        } else {
            punishment = punishment_de;
            haircolor = haircolor_de;
            clothes = clothes_de;
            accessories = accessories_de;
            height = height_de;
            switch (level) {
                case 1: {
                    flirt = flirt_level1_de;
                    break;
                }
                case 2: {
                    flirt = Stream.concat(
                            Arrays.stream(flirt_level1_de),
                            Arrays.stream(flirt_level2_de)
                    ).toArray(String[]::new);
                    break;
                }
                default: {
                    flirt = Stream.concat(
                            Arrays.stream(flirt_level1_de),
                            Arrays.stream(flirt_level2_de)
                    ).toArray(String[]::new);
                    flirt = Stream.concat(
                            Arrays.stream(flirt),
                            Arrays.stream(flirt_level3_de)
                    ).toArray(String[]::new);
                    break;
                }
            }
        }
    }

    public String[] getPunishment() {
        return punishment;
    }

    public void setPunishment(String[] punishment) {
        this.punishment = punishment;
    }

    public String[] getHaircolor() {
        return haircolor;
    }

    public void setHaircolor(String[] haircolor) {
        this.haircolor = haircolor;
    }

    public String[] getClothes() {
        return clothes;
    }

    public void setClothes(String[] clothes) {
        this.clothes = clothes;
    }

    public String[] getAccessories() {
        return accessories;
    }

    public void setClothes_color(String[] clothes_color) {
        this.accessories = clothes_color;
    }

    public String[] getHeight() {
        return height;
    }

    public void setHeight(String[] height) {
        this.height = height;
    }

    public String[] getFlirt() {
        return flirt;
    }

    public void setFlirt(String[] flirt) {
        this.flirt = flirt;
    }
}
