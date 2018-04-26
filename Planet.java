/**
* From https://docs.oracle.com/javase/tutorial/java/javaOO/enum.html
*
* Java enum's are pretty sophisticated, n'est-ce pas...? 
*/
public enum Planet {
    // PLANET ( mass(kg), radius(m) )
    THE_SUN (1.98855e+30, 695.700e6),
    MERCURY (3.303e+23, 2.4397e6),
    VENUS   (4.869e+24, 6.0518e6),
    EARTH   (5.976e+24, 6.37814e6),
    THE_MOON   (7.342e+22, 1.7371e6),
    MARS    (6.421e+23, 3.3972e6),
    JUPITER (1.9e+27,   7.1492e7),
    SATURN  (5.688e+26, 6.0268e7),
    URANUS  (8.686e+25, 2.5559e7),
    NEPTUNE (1.024e+26, 2.4746e7),
    PLUTO (1.303e+22, 1.188e6)
    ;

    // https://en.wikipedia.org/wiki/Sun
    // Equatorial radius	695,700 km,[6] = 695.700 x 10^3 x 10^3 m = 695.700 x 10^6 m
    // 696,392 km[7]
    // 109 × Earth[8]
    //
    // Mass	(1.98855±0.00025)×10^30 kg[1] = 1.98855 x 10^30 kg 
    // 333,000 × Earth[1]
    // Average density	1.408 g/cm3[1][8][9]
    // 0.255 × Earth[1][8]
    // Center density (modeled)	162.2 g/cm3[1]
    // 12.4 × Earth
    // Equatorial surface gravity	274.0 m/s2[1]
    // 27.94 g
    // 27,542.29 cgs
    // 28 × Earth[8]

    //https://en.wikipedia.org/wiki/Moon
    // Mean radius
    // 1737.1 km  (0.273 of Earth's)[1][4][5] = 1.7371 x 10^6 kg
    //
    // Mass	7.342 × 10^22 kg  (0.012300 of Earth's)[1][4]
    //
    // Mean density:
    // 3.344 g/cm3[1][4]
    // 0.606 × Earth
    // Surface gravity:
    // 1.62 m/s2  (0.1654 g)[4]

    //https://en.wikipedia.org/wiki/Mars
    // Mean radius
    // 3389.5 ± 0.2 km or 3,389.6 km or 3.389 x 10^3 x 10^3 m = 3.389 x 10^6 m
    // (2106.1 ± 0.1 mi)
    //
    // Mass	6.4171×10^23 kg[6]
    // (0.107 Earths)

    // https://en.wikipedia.org/wiki/Pluto
    //
    // Mean radius: 1,188.3±0.8 km[1][5] or 1,188 km or 1.188 x 10^6 m
    //              0.1868 Earths
    // Mass:	(1.303±0.003)×10^22 kg[6] or 1.303 x 10^22 kg
    //          0.00218 Earths
    //          0.177 Moons/
    // Surface gravity:
    //          0.620 m/s2[e]
    //          0.063 g

    private final double mass;   // in kilograms

    private final double radius; // in meters

    Planet(double mass, double radius) {
        this.mass = mass;
        this.radius = radius;
    }
    private double mass() { return mass; }
    private double radius() { return radius; }

    // universal gravitational constant  (m3 kg-1 s-2)
    public static final double G = 6.67300E-11;

    double surfaceGravity() {
        return G * mass / (radius * radius);
    }
    double surfaceWeight(double otherMass) {
        return otherMass * surfaceGravity();
    }
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Usage: java Planet <earth_weight>");
            System.exit(-1);
        }

        double earthWeight = Double.parseDouble(args[0]);
        System.out.printf("earthweight = %f pounds%n", earthWeight);

        double mass = earthWeight/EARTH.surfaceGravity();
        System.out.printf("mass = earthWeight / EARTH.surfaceGravity() = %f/%f = %f lbs-sec^2/m %n", earthWeight, EARTH.surfaceGravity(), mass );

        System.out.printf("%n");

        for (Planet p : Planet.values())
           System.out.printf("Your weight on %8s is %7.2f pounds = %2.4f of your earthweight.%n",
                             p, p.surfaceWeight(mass), p.surfaceWeight(mass)/earthWeight);
    }
}
