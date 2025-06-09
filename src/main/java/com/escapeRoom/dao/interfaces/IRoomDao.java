package com.escapeRoom.dao.interfaces;

import com.escapeRoom.entities.Room;
import com.escapeRoom.entities.enums.Difficulty;
import com.escapeRoom.entities.enums.Theme;

import java.math.BigDecimal;

public interface IRoomDao extends IGenericDAO<Room, Integer> {
    boolean updateRoomName(int roomId, String newName);
    boolean updateRoomPrice(int roomId, BigDecimal newPrice);
    boolean updateRoomDifficulty(int roomId, Difficulty difficulty);
    boolean updateRoomTheme(int roomId, Theme theme);
}
