package database;

/**
 * Created by Alex on 8/28/2015.
 */
public class Models {

    static class Champion {
        private int id;
        private String name;

        public Champion(String name, int id) {
            this.name = name;
            this.id = id;
        }
    }
}
