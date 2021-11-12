package ru.vsu.cs.savenkova_a_v;

import java.util.Locale;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Locale.setDefault(Locale.ROOT);

        int month = readMonth();
        month = isMonthReal(month);

        int number = readNumber();
        number = doesNumberExistInMonth(month, number);

        int day = isDayWeekendOrWorking(month, number);

        printAnswer(day);
    }

    private static int isDayWeekendOrWorking(int month, int number) {
        int day;
        int year = 2021;
        int weekday = findWeekday(number, month, year);
        if (weekday == 0) {
            day = 1;
        } else if ((weekday == 6) && (month != 2)) {
            day = 1;
        } else if ((month == 1) && (number <= 10)) {
            day = 1;
        } else if ((month == 3) && (number == 8)) {
            day = 1;
        } else if ((month == 5) && (number == 3) || (number == 10)) {
            day = 1;
        } else if ((month == 6) && (number == 14)) {
            day = 1;
        } else if ((month == 11) && (number <= 7) && (number >= 4)) {
            day = 1;
        } else if ((month == 12) && (number == 31)) {
            day = 1;
        } else if ((month == 2) && ((weekday == 6) && (number != 20)) || ((number == 22) || (number == 23))) {
            day = 1;
        } else {
            day = 0;
        }
        return day;
    }

    private static int findWeekday(int number, int month, int year) {
        if ((month == 1) || (month == 2)) {
            year = year - 1;
            month = month + 10;
        } else {
            month = month - 2;
        }
        return (number + (31 * month) / 12 + year + year / 4 - year / 100 + year / 400) % 7;
    }

    private static int readMonth() {
        System.out.println("Введите номер месяца (от 1 до 12)");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    private static int isMonthReal(int m) {
        int month = 0;
        if (m < 13) {
            month = m;
        } else {
            System.out.println("Такого месяца нет");
            System.exit(0);
        }
        return month;
    }

    private static int readNumber() {
        System.out.println("Введите число месяца");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    private static int doesNumberExistInMonth(int month, int number) {
        int n = 0;
        if ((month == 2) && (number < 29)) {
            return number;
        } else if ((month == 1) || (month == 3) || (month == 5) || (month == 7) || (month == 8) || (month == 10) || (month == 12)) {
            if (number < 32) {
                return number;
            } else if ((month == 4) || (month == 6) || (month == 9) || (month == 11)) {
                if (n < 31) {
                    return number;
                } else {
                    System.out.println("Неправильное число");
                    System.exit(0);
                }
            }
        }
        return number;
    }

    private static void printAnswer(int day) {
        if (day == 1) {
            System.out.println("Выходной день");
        } else {
            System.out.println("Рабочий день");
        }
    }
}