package tmp.generated_haskell;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class constr1 extends constr {
  public constr1(ArrayList<vt> vt, naam naam, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyList<vt>("vt", vt),
      new PropertyOne<naam>("naam", naam)
    }, firstToken, lastToken);
  }
  public constr1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new constr1(cloneProperties(),firstToken,lastToken);
  }
  public ArrayList<vt> getVt() {
    return ((PropertyList<vt>)getProperty("vt")).getValue();
  }
  public naam getNaam() {
    return ((PropertyOne<naam>)getProperty("naam")).getValue();
  }
}
