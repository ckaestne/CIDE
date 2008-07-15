package tmp.generated_c;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class InitializerList extends GenASTNode {
  public InitializerList(Initializer initializer, ArrayList<Initializer> initializer1, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Initializer>("initializer", initializer),
      new PropertyZeroOrMore<Initializer>("initializer1", initializer1)
    }, firstToken, lastToken);
  }
  public InitializerList(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new InitializerList(cloneProperties(),firstToken,lastToken);
  }
  public Initializer getInitializer() {
    return ((PropertyOne<Initializer>)getProperty("initializer")).getValue();
  }
  public ArrayList<Initializer> getInitializer1() {
    return ((PropertyZeroOrMore<Initializer>)getProperty("initializer1")).getValue();
  }
}
