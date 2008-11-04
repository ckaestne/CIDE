package tmp.generated_antlr;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class altRewrite extends GenASTNode {
  public altRewrite(ArrayList<elementNoOptionSpec> elementNoOptionSpec, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOneOrMore<elementNoOptionSpec>("elementNoOptionSpec", elementNoOptionSpec)
    }, firstToken, lastToken);
  }
  public altRewrite(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new altRewrite(cloneProperties(),firstToken,lastToken);
  }
  public ArrayList<elementNoOptionSpec> getElementNoOptionSpec() {
    return ((PropertyOneOrMore<elementNoOptionSpec>)getProperty("elementNoOptionSpec")).getValue();
  }
}
