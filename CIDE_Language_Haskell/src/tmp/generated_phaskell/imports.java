package tmp.generated_phaskell;

import java.util.ArrayList;

import cide.gast.IASTNode;
import cide.gast.IToken;
import cide.gast.Property;
import cide.gast.PropertyOne;
import cide.gast.PropertyZeroOrMore;
import cide.gparser.Token;

public class imports extends GenASTNode {
  public imports(imp imp, ArrayList<imp> imp1, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<imp>("imp", imp),
      new PropertyZeroOrMore<imp>("imp1", imp1)
    }, firstToken, lastToken);
  }
  public imports(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new imports(cloneProperties(),firstToken,lastToken);
  }
  public imp getImp() {
    return ((PropertyOne<imp>)getProperty("imp")).getValue();
  }
  public ArrayList<imp> getImp1() {
    return ((PropertyZeroOrMore<imp>)getProperty("imp1")).getValue();
  }
}
