package tmp.generated_phaskell;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class context extends GenASTNode {
  public context(ASTStringNode finduntilsemiorcontextarrow, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrOne<ASTStringNode>("finduntilsemiorcontextarrow", finduntilsemiorcontextarrow)
    }, firstToken, lastToken);
  }
  public context(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new context(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getFinduntilsemiorcontextarrow() {
    return ((PropertyZeroOrOne<ASTStringNode>)getProperty("finduntilsemiorcontextarrow")).getValue();
  }
}
