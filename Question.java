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
public abstract class Question {
    private String description;
    private String answer;

    public void setDescription(String description) {
        this.description = description;
    }
    public void setAnswer(String answer){
        this.answer=answer;
    }
    public String getDescription() {
        return description;
    }
    public String getAnswer() {
        return answer;
    }
}