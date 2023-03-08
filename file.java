import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class RSA {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BigInteger p, q, N, phiN, e, d, m, c;

        System.out.println("Enter a prime number p:");
        p = new BigInteger(sc.nextLine());

        System.out.println("Enter a prime number q:");
        q = new BigInteger(sc.nextLine());

        N = p.multiply(q);
        phiN = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));

        System.out.println("Enter an integer e (1 < e < phi(N) and gcd(e,phi(N))=1):");
        e = new BigInteger(sc.nextLine());

        d = e.modInverse(phiN);

        System.out.println("Public key (N,e): (" + N + "," + e + ")");
        System.out.println("Private key (d): " + d);

        System.out.println("Enter the message to be encrypted:");
        String message = sc.nextLine();

        byte[] messageBytes = message.getBytes(StandardCharsets.UTF_8);
        m = new BigInteger(messageBytes);

        if (m.compareTo(N) >= 0) {
            System.out.println("Error: message too long");
            return;
        }

        c = m.modPow(e, N);

        System.out.println("Encrypted message: " + c);

        m = c.modPow(d, N);

        byte[] decryptedBytes = m.toByteArray();
        String decryptedMessage = new String(decryptedBytes, StandardCharsets.UTF_8);

        System.out.println("Decrypted message: " + decryptedMessage);
    }
}
