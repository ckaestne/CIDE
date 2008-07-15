package tmp.generated_bali;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Primitive2 extends Primitive {
  public Primitive2(Terminal terminal, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Terminal>("terminal", terminal)
    }, firstToken, lastToken);
  }
  public Primitive2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new Primitive2(cloneProperties(),firstToken,lastToken);
  }
  public Terminal getTerminal() {
    return ((PropertyOne<Terminal>)getProperty("terminal")).getValue();
  }
}
