package com.escapeRoom.roombuilder.interfaces;

import com.escapeRoom.roombuilder.Decoration;
import com.escapeRoom.roombuilder.Dificulty;
import com.escapeRoom.roombuilder.Hint;
import com.escapeRoom.roombuilder.Room;


public interface IRoomBuilder {
    IRoomBuilder setName(String name);
    IRoomBuilder addHints(Hint hints);
    IRoomBuilder addDecorations(Decoration decorations);
    IRoomBuilder setDificulty(Dificulty dificulty);
    IRoomBuilder setPrice(int price);
    Room build();
}
