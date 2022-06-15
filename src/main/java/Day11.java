import java.util.List;

import static java.lang.Integer.parseInt;

public class Day11 extends AOCHandler {

    public Day11() {
        super("11");
    }

    Octopus[][] octo;
    int flashes = 0;
    int flashPerStep = 0;

    void solve(List<String> input) {
        int s = 10;
        octo = new Octopus[s][s];
        int steps = 1000;
        for (int i = 0; i < input.size(); i++) {
            for (int j = 0; j < input.get(i).length(); j++) {
                octo[i][j] = new Octopus(false, parseInt(String.valueOf(input.get(i).charAt(j))));
            }
        }
        for (int k = 0; k < steps; k++) {

            octo = increase(octo);

            for (int i = 0; i < octo.length; i++) {
                for (int j = 0; j < octo.length; j++) {
                    Octopus oc = octo[i][j];
                    if (oc.energy > 9 && !oc.flashed) {
                        octo = increaseNeighbours(octo, i, j);
                    }
                }
            }

            for (int z = 0; z < octo.length; z++) {
                for (int l = 0; l < octo.length; l++) {
                    if (octo[z][l].flashed)
                        octo[z][l].energy = 0;
                    octo[z][l].flashed = false;
                }
            }
            if (flashPerStep == 100) {
                System.out.println("Part 2: " + (k + 1));
                break;
            }
            flashPerStep = 0;
            if (k + 1 == 100) {
                System.out.println("Part 1: " + flashes);
            }
        }
    }

    void printMap() {
        for (int i = 0; i < octo.length; i++) {
            for (int j = 0; j < octo.length; j++) {
                System.out.print(octo[i][j].energy);
            }
            System.out.println();
        }
        System.out.println();
    }

    static class Octopus {
        boolean flashed;
        int energy;

        public Octopus(boolean flashed, int energy) {
            this.flashed = flashed;
            this.energy = energy;
        }
    }

    Octopus[][] increaseNeighbours(Octopus[][] arr, int i, int j) {
        flashes++;
        flashPerStep++;
        arr[i][j].flashed = true;

        for (int xoff = -1; xoff < 2; xoff++) {
            for (int yoff = -1; yoff < 2; yoff++) {
                int i2 = xoff + i;
                int j2 = yoff + j;
                if (xoff == 0 && yoff == 0) continue;
                if (i2 > -1 && i2 < arr.length && j2 > -1 && j2 < arr.length) {
                    //increase all neighbours
                    Octopus current = arr[i2][j2];
                    current.energy++;
                }
            }
        }
        for (int xoff = -1; xoff < 2; xoff++) {
            for (int yoff = -1; yoff < 2; yoff++) {
                int i2 = xoff + i;
                int j2 = yoff + j;
                if (xoff == 0 && yoff == 0) continue;
                if (onMap(i2, j2)) {
                    //increase all neighbours
                    Octopus current = arr[i2][j2];
                    if (current.energy > 9 && !current.flashed) {
                        arr = increaseNeighbours(arr, i2, j2);
                    }
                }
            }
        }
        return arr;
    }

    Octopus[][] increase(Octopus[][] arr) {
        for (Octopus[] octupuses : arr) {
            for (Octopus a : octupuses) {
                a.energy += 1;
            }
        }
        return arr;
    }

    boolean onMap(int x, int y) {
        int n = 10;
        int m = 10;
        return x >= 0 && y >= 0 && x < n && y < m;
    }
}