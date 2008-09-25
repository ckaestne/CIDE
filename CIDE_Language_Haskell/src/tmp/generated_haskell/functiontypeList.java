package tmp.generated_haskell;

import java.util.ArrayList;

import cide.gast.IASTNode;
import cide.gast.IToken;
import cide.gast.Property;
import cide.gast.PropertyList;
import cide.gparser.Token;

public class functiontypeList extends GenASTNode {
  public functiontypeList(ArrayList<functiontype> functiontype, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyList<functiontype>("functiontype", functiontype)
    }, firstToken, lastToken);
  }
  public functiontypeList(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new functiontypeList(cloneProperties(),firstToken,lastToken);
  }
  public ArrayList<functiontype> getFunctiontype() {
    return ((PropertyList<functiontype>)getProperty("functiontype")).getValue();
  }
}
