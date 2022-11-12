import java.util.Random;

public class Main {
    public static int bossHealth = 700;
    public static int bossDamage = 50;
    public static int[] heroesHealth = {280, 270, 250, 400, 500, 200, 200 , 250};
    public static int[] heroesDamage = {10, 15, 20, 0, 5, 5, 15, 10};
    public static String[] heroesClass = {"Warrior", "Mage", "Slayer", "Medic", "Golem", "Lucky" , "Berserk", "Thor"};
    public static int roundNumber;

    public static int reflect = 0;

    public  static boolean dodge;

    public static int block = 0;

    public static boolean stun;

    public static void main(String[] args) {
        printStatistics();
        while (!isGameFinished()) {
            round();
        }
    }
    public static void heal() {
        Random random = new Random();
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] < 100 && heroesHealth[3] > 0) {
                int target = random.nextInt(heroesHealth.length);
                while (true) {
                    target = random.nextInt(heroesHealth.length);
                    if (heroesHealth[target] < 100 && heroesHealth[target] >= 0 && heroesHealth[target] != 3) {
                        break;
                    }
                }
                if (target != 3 & heroesHealth[target] < 100 & heroesHealth[target] > 0) {
                    heroesHealth[target] = heroesHealth[target] + 100;
                    System.out.println("Heal :" + heroesClass[target]);
                    break;
                }
            }
        }
    }

    public static void round() {
        roundNumber++;
        bossHits();
        heroesHit();
        printStatistics();
        heal();
    }

    public static void bossHits() {
        int heroesAlive = -1;
        Random random = new Random();
        dodge = false;
        dodge = random.nextBoolean();
        int blockAndReflect;
        for (int i = 0; i < heroesHealth.length; i++) {
            if (stun == true){
                System.out.println("Boss stunned");
                stun = false;
                break;
            }
            if (heroesClass[i] == heroesClass[5] && dodge == true) {
                System.out.println(heroesClass[5] + " Dodge ");
                continue;
            }
            if (heroesHealth[i] > 0) {
                if (heroesHealth[i] - bossDamage < 0) {
                    heroesHealth[i] = 0;
                } else {
                    heroesAlive = heroesAlive + 1;
                    if (heroesHealth[6] > 0 && heroesClass[i] == heroesClass[6]) {
                        reflect = bossDamage / 5;
                        blockAndReflect = bossDamage - reflect - ((bossDamage - reflect) / 5);
                        bossHealth = bossHealth - reflect;
                        System.out.println("Reflect : "  + reflect);
                        heroesHealth[6] = heroesHealth[6] - blockAndReflect;
                        continue;
                    } else if (heroesClass[i] != heroesClass[6] && heroesHealth[4] > 0) {
                        heroesHealth[i] = heroesHealth[i] - (bossDamage - (bossDamage / 5));
                    } else {
                        heroesHealth[i] = heroesHealth[i] - bossDamage;
                    }
                    if (heroesHealth[4] > 0) {
                        block = bossDamage / 5 * heroesAlive;
                        heroesHealth[4] = heroesHealth[4] - (bossDamage + block);
                        System.out.println("Blocked: " + block);
                        if (heroesHealth[4] < bossDamage) {
                            heroesHealth[4] = heroesHealth[4] = 0;
                        }
                    }
                }
            }
        }
    }

    public static void heroesHit() {
        Random random = new Random();
        for (int i = 0; i < heroesDamage.length; i++) {
            if (heroesClass[i] == heroesClass[7] && heroesHealth[7] > 0)
                stun = random.nextBoolean();
            if (heroesHealth[i] > 0 && bossHealth > 0) {
                bossHealth = bossHealth - heroesDamage[i];
                }
            }
        }

    public static void printStatistics() {
        /*String defence;
        if (bossDefenceType == null) {
            defence = "No defence";
        } else {
            defence = bossDefenceType;
        }*/
        System.out.println("ROUND " + roundNumber + " ------------");
        System.out.println("Boss health: " + bossHealth + " damage: " + bossDamage);
        for (int i = 0; i < heroesHealth.length; i++) {
            System.out.println(heroesClass[i] + " health: "
                    + heroesHealth[i] + " damage: " + heroesDamage[i]);
        }
    }

    public static boolean isGameFinished() {
        if (bossHealth <= 0) {
            System.out.println("Heroes won!!!");
            return true;
        }
       /* if (heroesHealth[0] <= 0 && heroesHealth[1] <= 0 && heroesHealth[2] <= 0) {
            System.out.println("Boss won!!!");
            return true;
        }
        return false;*/
        boolean allHeroesDead = true;
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                allHeroesDead = false;
                break;
            }
        }
        if (allHeroesDead) {
            System.out.println("Boss won!!!");
        }
        return allHeroesDead;
    }
}
