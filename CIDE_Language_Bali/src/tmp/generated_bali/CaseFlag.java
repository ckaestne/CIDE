package tmp.generated_bali;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class CaseFlag extends GenASTNode {
  public CaseFlag(ASTStringNode _ignore_case, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("_ignore_case", _ignore_case)
    }, firstToken, lastToken);
  }
  public CaseFlag(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new CaseFlag(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode get_ignore_case() {
    return ((PropertyOne<ASTStringNode>)getProperty("_ignore_case")).getValue();
  }
}
