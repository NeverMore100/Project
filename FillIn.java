import java.lang.management.GarbageCollectorMXBean;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.lang.reflect.Array;
import java.util.ArrayList.*;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.*;
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import javax.swing.*;
public class FillIn extends Question {
    @Override
    public String toString() {
        return getDescription().replace("{blank}", "___");
    }
}