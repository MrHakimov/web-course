package ru.itmo.tpl.util;

import ru.itmo.tpl.model.Post;
import ru.itmo.tpl.model.User;

import java.util.*;

public class DataUtil {
    private static final List<User> USERS = Arrays.asList(
            new User(1, "MikeMirayanov", "Mikhail Mirzayanov", User.Color.GREEN),
            new User(2, "tourist", "Genady Korotkevich", User.Color.RED),
            new User(3, "emusk", "Elon Musk", User.Color.RED),
            new User(5, "pashka", "Pavel Mavrin", User.Color.RED),
            new User(7, "geranazavr555", "Georgiy Nazarov", User.Color.BLUE),
            new User(11, "cannon147", "Erofey Bashunov", User.Color.BLUE)
    );

    private static final List<Post> POSTS = Arrays.asList(
            new Post(1, "New on Codeforces: Allowed Programming Languages Customization for Mashups/Private " +
                    "Trainings", "Hello!\n" +
                    "\n" +
                    "Codeforces supports a wide range of languages. Just yesterday I supported Java 11.\n" +
                    "\n" +
                    "On the other hand, my website is not only just open public rounds and trainings, but also an " +
                    "advanced system of groups, custom domains and mashups. This ecosystem makes it possible to " +
                    "conduct private trainings, local contests/olympiads, provides the work of computer science" +
                    " clubs, and so on.\n" +
                    "\n" +
                    "Sometimes you ask me to somehow limit the languages that can be used in a particular contest. " +
                    "For example, an official competition is held that does not support Rust. Or this contest is " +
                    "a practical session specifically for classes in Python.\n" +
                    "\n" +
                    "Now you can configure allowed programming languages (more precisely, their groups) for your" +
                    " mashups and private trainings. Thanks for the help to geranazavr555 who helped to realize " +
                    "this opportunity!", 1),
            new Post(2, "Live online mirror of The 2019 ICPC Asia Jakarta Regional Contest", "Hi,\n"+
                                 "\n"+
                                 "We would like to invite you to participate in the live (with 30-minute delay) " +
                    "online mirror contest of The 2019 ICPC Asia Jakarta Regional Contest (our regional website, " +
                    "our regional in ICPC website, official contest portal) this weekend. The online mirror contest" +
                    " will start on Sunday, October 27, 2019 at 06:30.\n"+
                                 "\n"+
                                 "The contest consists of several problems and you can solve them in 5 hours.\n"+
                                 "\n"+
                                 "See you on top of the leaderboard.", 11),
            new Post(5, "About the Failed Round 591/Technocup 2020 — Elimination Round 1",
                    "Hi Codeforces!\n" +
                    "\n" +
                    "Unfortunately, ill-wishers thwarted the round by making DDOS on our infrastructure.", 1),
            new Post(9, "Huawei Honorcup Marathon 2", "Dear Friends!\n" +
                    "\n" +
                    "If you dream of realizing your ideas in new technologies and products used by a third part of" +
                    " the world's population — join Honorcup Marathon. It will be an unrated round for individual " +
                    "participation, as well as for teams of up to three people.", 7)
    );

    private static List<User> getUsers() {
        return USERS;
    }

    private static List<Post> getPosts() {
        return POSTS;
    }

    public static void putData(Map<String, Object> data) {
        data.put("users", getUsers());
        List<Post> tmp = new ArrayList<>(getPosts());
        Collections.reverse(tmp);
        data.put("posts", tmp);

        for (User user : getUsers()) {
            if (Long.toString(user.getId()).equals(data.get("logged_user_id"))) {
                data.put("user", user);
            }
        }
    }
}
