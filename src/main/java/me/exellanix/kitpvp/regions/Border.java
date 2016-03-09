package me.exellanix.kitpvp.regions;

import org.bukkit.Location;
import org.bukkit.block.Block;

/**
 * Created by Mac on 3/8/2016.
 */
public class Border {
    private Selection north;
    private Selection south;
    private Selection east;
    private Selection west;
    private int size;

    public Border(Block first, Block second, int size) {
        this.size = size;
        north = new Selection();
        south = new Selection();
        east = new Selection();
        west = new Selection();
        setup(first, second);
    }

    public Selection getNorth() {
        return north;
    }

    public Selection getSouth() {
        return south;
    }

    public Selection getEast() {
        return east;
    }

    public Selection getWest() {
        return west;
    }

    public int getSize() {
        return size;
    }

    private void setup(Block first, Block second) {
        if (first.getX() > second.getX()) {
            if (first.getZ() > second.getZ()) {
                Location northF = second.getLocation();
                Location northS = second.getLocation();
                northS.setX(first.getX() - size);
                northS.setZ(northS.getZ() + size);
                north.setFirst(northF.getBlock());
                north.setSecond(northS.getBlock());

                Location southF = first.getLocation();
                Location southS = first.getLocation();
                southS.setX(second.getX() + size);
                southS.setZ(southS.getZ() - size);
                south.setFirst(southF.getBlock());
                south.setSecond(southS.getBlock());

                Location eastF = first.getLocation();
                Location eastS = first.getLocation();
                eastS.setX(first.getX() - size);
                eastS.setZ(second.getZ() + size);
                east.setFirst(eastF.getBlock());
                east.setSecond(eastS.getBlock());

                Location westF = second.getLocation();
                Location westS = second.getLocation();
                westS.setX(second.getX() + size);
                westS.setZ(first.getZ() - size);
                west.setFirst(westF.getBlock());
                west.setSecond(westS.getBlock());
            } else {
                // First x is larger, Second Z is larger
                Location northF = first.getLocation();
                Location northS = first.getLocation();
                northS.setX(second.getX() + size);
                northS.setZ(northS.getZ() + size);
                north.setFirst(northF.getBlock());
                north.setSecond(northS.getBlock());

                Location southF = second.getLocation();
                Location southS = second.getLocation();
                southS.setX(first.getX() - size);
                southS.setZ(southS.getZ() - size);
                south.setFirst(southF.getBlock());
                south.setSecond(southS.getBlock());

                Location eastF = first.getLocation();
                Location eastS = first.getLocation();
                eastS.setX(first.getX() - size);
                eastS.setZ(second.getZ() - size);
                east.setFirst(eastF.getBlock());
                east.setSecond(eastS.getBlock());

                Location westF = second.getLocation();
                Location westS = second.getLocation();
                westS.setX(second.getX() + size);
                westS.setZ(first.getZ() + size);
                west.setFirst(westF.getBlock());
                west.setSecond(westS.getBlock());
            }
        } else {
            if (first.getZ() > second.getZ()) {
                // Second x is larger, First Z is larger
                Location northF = second.getLocation();
                Location northS = second.getLocation();
                northS.setX(first.getX() + size);
                northS.setZ(northS.getZ() + size);
                north.setFirst(northF.getBlock());
                north.setSecond(northS.getBlock());

                Location southF = first.getLocation();
                Location southS = first.getLocation();
                southS.setX(second.getX() - size);
                southS.setZ(southS.getZ() - size);
                south.setFirst(southF.getBlock());
                south.setSecond(southS.getBlock());

                Location eastF = second.getLocation();
                Location eastS = second.getLocation();
                eastS.setX(second.getX() - size);
                eastS.setZ(first.getZ() - size);
                east.setFirst(eastF.getBlock());
                east.setSecond(eastS.getBlock());

                Location westF = first.getLocation();
                Location westS = first.getLocation();
                westS.setX(first.getX() + size);
                westS.setZ(second.getZ() + size);
                west.setFirst(westF.getBlock());
                west.setSecond(westS.getBlock());
            } else {
                // Second x is larger, Second Z is larger
                Location northF = first.getLocation();
                Location northS = first.getLocation();
                northS.setX(second.getX() - size);
                northS.setZ(northS.getZ() + size);
                north.setFirst(northF.getBlock());
                north.setSecond(northS.getBlock());

                Location southF = second.getLocation();
                Location southS = second.getLocation();
                southS.setX(first.getX() + size);
                southS.setZ(southS.getZ() - size);
                south.setFirst(southF.getBlock());
                south.setSecond(southS.getBlock());

                Location eastF = second.getLocation();
                Location eastS = second.getLocation();
                eastS.setX(second.getX() - size);
                eastS.setZ(first.getZ() + size);
                east.setFirst(eastF.getBlock());
                east.setSecond(eastS.getBlock());

                Location westF = first.getLocation();
                Location westS = first.getLocation();
                westS.setX(first.getX() + size);
                westS.setZ(second.getZ() - size);
                west.setFirst(westF.getBlock());
                west.setSecond(westS.getBlock());
            }
        }
    }
}
