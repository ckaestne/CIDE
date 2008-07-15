package tmp.generated_haskell;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class patrMain1 extends patrMain {
  public patrMain1(naam naam, ArrayList<patroon> patroon, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<naam>("naam", naam),
      new PropertyOneOrMore<patroon>("patroon", patroon)
    }, firstToken, lastToken);
  }
  public patrMain1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new patrMain1(cloneProperties(),firstToken,lastToken);
  }
  public naam getNaam() {
    return ((PropertyOne<naam>)getProperty("naam")).getValue();
  }
  public ArrayList<patroon> getPatroon() {
    return ((PropertyOneOrMore<patroon>)getProperty("patroon")).getValue();
  }
}
