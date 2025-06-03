package com.escapeRoom.model.interfaces;

import com.escapeRoom.model.Decoration;
import com.escapeRoom.model.Dificulty;
import com.escapeRoom.model.Hint;
import com.escapeRoom.model.Room;


public interface IRoomBuilder {
    IRoomBuilder setName(String name);
    IRoomBuilder addHints(Hint hints);
    IRoomBuilder addDecorations(Decoration decorations);
    IRoomBuilder setDificulty(Dificulty dificulty);
    IRoomBuilder setPrice(int price);
    Room build();
}
