package tmp.generated_antlr;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class atom1 extends atom {
  public atom1(terminal terminal, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<terminal>("terminal", terminal)
    }, firstToken, lastToken);
  }
  public atom1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new atom1(cloneProperties(),firstToken,lastToken);
  }
  public terminal getTerminal() {
    return ((PropertyOne<terminal>)getProperty("terminal")).getValue();
  }
}
