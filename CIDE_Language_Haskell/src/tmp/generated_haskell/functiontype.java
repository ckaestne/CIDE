package tmp.generated_haskell;

import java.util.ArrayList;

import cide.gast.IASTNode;
import cide.gast.IToken;
import cide.gast.Property;
import cide.gast.PropertyList;
import cide.gparser.Token;

public class functiontype extends GenASTNode {
  public functiontype(ArrayList<paramtype> paramtype, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyList<paramtype>("paramtype", paramtype)
    }, firstToken, lastToken);
  }
  public functiontype(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new functiontype(cloneProperties(),firstToken,lastToken);
  }
  public ArrayList<paramtype> getParamtype() {
    return ((PropertyList<paramtype>)getProperty("paramtype")).getValue();
  }
}
