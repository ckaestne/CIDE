package tmp.generated_phaskell;

import java.util.ArrayList;

import cide.gast.IASTNode;
import cide.gast.IToken;
import cide.gast.Property;
import cide.gast.PropertyList;
import cide.gparser.Token;

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
