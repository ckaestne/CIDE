package tmp.generated_haskell;

import java.util.ArrayList;

import cide.gast.IASTNode;
import cide.gast.IToken;
import cide.gast.Property;
import cide.gast.PropertyOneOrMore;
import cide.gparser.Token;

public class paramtype extends GenASTNode {
  public paramtype(ArrayList<type> type, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOneOrMore<type>("type", type)
    }, firstToken, lastToken);
  }
  public paramtype(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new paramtype(cloneProperties(),firstToken,lastToken);
  }
  public ArrayList<type> getType() {
    return ((PropertyOneOrMore<type>)getProperty("type")).getValue();
  }
}
