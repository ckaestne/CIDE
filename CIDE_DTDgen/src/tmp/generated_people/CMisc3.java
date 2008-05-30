package tmp.generated_people;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class CMisc3 extends CMisc {
  public CMisc3(Whitespace whitespace, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Whitespace>("whitespace", whitespace)
    }, firstToken, lastToken);
  }
  public CMisc3(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new CMisc3(cloneProperties(),firstToken,lastToken);
  }
  public Whitespace getWhitespace() {
    return ((PropertyOne<Whitespace>)getProperty("whitespace")).getValue();
  }
}
