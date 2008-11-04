package tmp.generated_haskell;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class constr1 extends constr {
  public constr1(ArrayList<fielddeclaration> fielddeclaration, naam naam, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyList<fielddeclaration>("fielddeclaration", fielddeclaration),
      new PropertyOne<naam>("naam", naam)
    }, firstToken, lastToken);
  }
  public constr1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new constr1(cloneProperties(),firstToken,lastToken);
  }
  public ArrayList<fielddeclaration> getFielddeclaration() {
    return ((PropertyList<fielddeclaration>)getProperty("fielddeclaration")).getValue();
  }
  public naam getNaam() {
    return ((PropertyOne<naam>)getProperty("naam")).getValue();
  }
}
