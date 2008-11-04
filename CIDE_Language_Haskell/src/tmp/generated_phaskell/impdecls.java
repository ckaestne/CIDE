package tmp.generated_phaskell;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class impdecls extends GenASTNode {
  public impdecls(ArrayList<impdecl> impdecl, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyList<impdecl>("impdecl", impdecl)
    }, firstToken, lastToken);
  }
  public impdecls(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new impdecls(cloneProperties(),firstToken,lastToken);
  }
  public ArrayList<impdecl> getImpdecl() {
    return ((PropertyList<impdecl>)getProperty("impdecl")).getValue();
  }
}
