/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tournament;

import java.util.Scanner;


public class Tournament {


    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);


        String[] teams = {"Team A", "Team B", "Team C", "Team D"};
        int[] homeTeam = {0, 2, 0, 1, 0, 1};   // her maçın 1. takımının indeksi
        int[] awayTeam = {1, 3, 2, 3, 3, 2};   // her maçın 2. takımının indeksi

        int matchCount = homeTeam.length;
        int teamCount = teams.length;

        
        int[] played = new int[teamCount];
        int[] wins = new int[teamCount];
        int[] draws = new int[teamCount];
        int[] losses = new int[teamCount];
        int[] points = new int[teamCount];
        int[] goalsFor = new int[teamCount];
        int[] goalsAgainst = new int[teamCount];
        int[] goalDiff = new int[teamCount];

        
        System.out.println("===== TOURNAMENT FIXTURE =====");
        for (int i = 0; i < matchCount; i++) {
            System.out.println("Match " + (i + 1) + ": "
                    + teams[homeTeam[i]] + " vs " + teams[awayTeam[i]]);
        }

        int[] homeGoals = new int[matchCount];
        int[] awayGoals = new int[matchCount];

        System.out.println("\n===== ENTER MATCH SCORES =====");
        for (int i = 0; i < matchCount; i++) {
            System.out.println("\nMatch " + (i + 1) + ": "
                    + teams[homeTeam[i]] + " vs " + teams[awayTeam[i]]);
            homeGoals[i] = readGoals(input, teams[homeTeam[i]] + " goals: ");
            awayGoals[i] = readGoals(input, teams[awayTeam[i]] + " goals: ");
        }

        System.out.println("\n===== MATCH RESULTS =====");
        for (int i = 0; i < matchCount; i++) {
            System.out.println("Match " + (i + 1) + ": "
                    + teams[homeTeam[i]] + " " + homeGoals[i] + " - "
                    + awayGoals[i] + " " + teams[awayTeam[i]]);
        }

        
        for (int i = 0; i < matchCount; i++) {
            int h = homeTeam[i];
            int a = awayTeam[i];

            played[h]++;
            played[a]++;

            
            goalsFor[h] += homeGoals[i];
            goalsAgainst[h] += awayGoals[i];
            goalsFor[a] += awayGoals[i];
            goalsAgainst[a] += homeGoals[i];

            
            if (homeGoals[i] == awayGoals[i]) {          
                points[h] += 1;
                points[a] += 1;
                draws[h]++;
                draws[a]++;
            } else if (homeGoals[i] > awayGoals[i]) {    // home wins
                points[h] += 3;
                wins[h]++;
                losses[a]++;
            } else {                                     // away wins
                points[a] += 3;
                wins[a]++;
                losses[h]++;
            }
        }

        for (int t = 0; t < teamCount; t++) {
            goalDiff[t] = goalsFor[t] - goalsAgainst[t];
        }

        
        System.out.println("\n===== STANDINGS TABLE =====");
        System.out.printf("%-10s %3s %3s %3s %3s %5s %5s%n",
                "Team", "P", "W", "D", "L", "Pts", "GD");
        System.out.println("-------------------------------------------");
        for (int t = 0; t < teamCount; t++) {
            System.out.printf("%-10s %3d %3d %3d %3d %5d %+5d%n",
                    teams[t], played[t], wins[t], draws[t], losses[t],
                    points[t], goalDiff[t]);
        }
        System.out.println("(P: Played, W: Wins, D: Draws, L: Losses, Pts: Points, GD: Goal Difference)");

        
        int champion = 0;
        for (int t = 1; t < teamCount; t++) {
            if (points[t] > points[champion]
                    || (points[t] == points[champion] && goalDiff[t] > goalDiff[champion])) {
                champion = t;
            }
        }

        
        boolean stillTied = false;
        for (int t = 0; t < teamCount; t++) {
            if (t != champion && points[t] == points[champion]
                    && goalDiff[t] == goalDiff[champion]) {
                stillTied = true;
            }
        }

        System.out.println();
        if (stillTied) {
            System.out.println("Teams are level on both points and goal difference. Tournament Champion: "
                    + teams[champion] + " (tie remains, please check the rules)");
        } else {
            System.out.println("Tournament Champion: " + teams[champion]);
        }

        input.close();
    }

    
    private static int readGoals(Scanner input, String message) {
        int value;
        while (true) {
            System.out.print(message);
            try {
                value = Integer.parseInt(input.nextLine().trim());
                if (value >= 0) {
                    return value;
                }
            } catch (NumberFormatException e) {
                // aşağıdaki mesaja düşer
            }
            System.out.println("Please enter a valid non-negative integer.");
        }
    }
}
    
