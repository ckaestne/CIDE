package tmp.generated_xhtml;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Misc2 extends Misc {
  public Misc2(Whitespace whitespace, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Whitespace>("whitespace", whitespace)
    }, firstToken, lastToken);
  }
  public Misc2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Misc2(cloneProperties(),firstToken,lastToken);
  }
  public Whitespace getWhitespace() {
    return ((PropertyOne<Whitespace>)getProperty("whitespace")).getValue();
  }
}
