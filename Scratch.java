import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

class Scratch {
    public static void main(String[] args) {
        List<Point> punti = getPointsUntil(100000000);
        var tuttiPunti = allPoints(punti).stream().flatMap(Collection::stream).collect(Collectors.toSet());

        List<Long> output = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        var total = scanner.nextInt();
        scanner.nextLine();
        while (total > 0) {
            var input = scanner.nextLine().split(" ");
            var maxX = Integer.parseInt(input[0]);
            var maxY = Integer.parseInt(input[1]);

            output.add(countPointWithin(tuttiPunti, maxX, maxY));

            total -= 1;
        }

        for (Long l : output) {
            System.out.println(l);
        }
    }

    private static List<Point> getPointsUntil(int max) {
        List<Point> punti = new ArrayList<>();

        int points = 1;
        int pos = 0;
        while (pos <= max) {
            var point = new Point();
            point.x = pos;
            punti.add(point);

            pos += (int) Math.pow(2, points - 1);
            points += 1;
        }
        return punti;
    }

    public static List<List<Point>> allPoints(List<Point> base) {
        var tutto = new ArrayList<List<Point>>();
        tutto.add(base);

        for (int i = 0; i < base.size(); i++) {
            base = base.stream()
                    .map(p -> {
                                var traslato = new Point();
                                traslato.y = p.y + 1;
                                traslato.x = p.x - 1;
                                return traslato;
                            }
                    ).collect(Collectors.toList());

            base = base.stream().filter(p -> p.x >= 0).collect(Collectors.toList());

            tutto.add(base);
        }

        return tutto;
    }
    public static long countPointWithin(Collection<Point> points, int maxX, int maxY) {
        return points.stream()
                .filter(p -> p.x <= maxX && p.y <= maxY)
                .count();
    }

    public static class Point {
        int x;
        int y;

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Point point = (Point) o;
            return x == point.x && y == point.y;
        }

        @Override
        public String toString() {
            return "{" + x + "," + y + "}";
        }
    }

}