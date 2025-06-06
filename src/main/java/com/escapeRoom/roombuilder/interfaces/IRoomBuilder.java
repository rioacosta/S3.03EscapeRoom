package com.escapeRoom.roombuilder.interfaces;

import com.escapeRoom.entities.Decoration;
import com.escapeRoom.entities.Hint;
import com.escapeRoom.entities.Room;
import com.escapeRoom.entities.enums.Difficulty;

import java.math.BigDecimal;


public interface IRoomBuilder {
    IRoomBuilder setIdEscapeRoom_ref(int idEscapeRoom_ref);
    IRoomBuilder setName(String name);
    IRoomBuilder addHints(Hint hints);
    IRoomBuilder addDecorations(Decoration decorations);
    IRoomBuilder setDificulty(Difficulty dificulty);
    IRoomBuilder setPrice(BigDecimal price);
    Room build();
}
