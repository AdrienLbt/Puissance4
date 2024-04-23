package GameAssets;

public class Tile {

        private int tileOwner;

        public Tile(int size) {
            this.tileOwner = 0;
        }
        public void setTileOwner (int tileOwner) {
            this.tileOwner = tileOwner;
        }
        public int getTileOwner() {
            return tileOwner;
        }
}