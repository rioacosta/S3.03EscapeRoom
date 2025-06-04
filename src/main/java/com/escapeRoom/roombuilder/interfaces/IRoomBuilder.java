package com.escapeRoom.roombuilder.interfaces;

import com.escapeRoom.entities.Decoration;
import com.escapeRoom.entities.Hint;
import com.escapeRoom.entities.Room;
import com.escapeRoom.entities.enums.Difficulty;


public interface IRoomBuilder {
    IRoomBuilder setName(String name);
    IRoomBuilder addHints(Hint hints);
    IRoomBuilder addDecorations(Decoration decorations);
    IRoomBuilder setDificulty(Difficulty dificulty);
    IRoomBuilder setPrice(int price);
    Room build();
}
