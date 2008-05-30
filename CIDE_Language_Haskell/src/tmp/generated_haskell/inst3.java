package tmp.generated_haskell;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class inst3 extends inst {
  public inst3(ArrayList<var> var2, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyList<var>("var2", var2)
    }, firstToken, lastToken);
  }
  public inst3(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new inst3(cloneProperties(),firstToken,lastToken);
  }
  public ArrayList<var> getVar2() {
    return ((PropertyList<var>)getProperty("var2")).getValue();
  }
}
