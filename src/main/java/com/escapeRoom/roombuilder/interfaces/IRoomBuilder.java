package com.escapeRoom.roombuilder.interfaces;

import com.escapeRoom.entities.Decoration;
import com.escapeRoom.entities.Hint;
import com.escapeRoom.entities.Room;
import com.escapeRoom.entities.enums.Difficulty;

import java.math.BigDecimal;
import java.util.List;


public interface IRoomBuilder {
    IRoomBuilder setIdEscapeRoom_ref(int idEscapeRoom_ref);
    IRoomBuilder setName(String name);
    IRoomBuilder setHints(List<Hint> hints);
    IRoomBuilder setDecorations(List<Decoration> decorations);
    IRoomBuilder setDificulty(Difficulty dificulty);
    IRoomBuilder setPrice(BigDecimal price);
    Room build();
}
