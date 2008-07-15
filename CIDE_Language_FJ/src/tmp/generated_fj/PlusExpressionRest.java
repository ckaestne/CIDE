package tmp.generated_fj;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class PlusExpressionRest extends GenASTNode {
  public PlusExpressionRest(Term term, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Term>("term", term)
    }, firstToken, lastToken);
  }
  public PlusExpressionRest(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new PlusExpressionRest(cloneProperties(),firstToken,lastToken);
  }
  public Term getTerm() {
    return ((PropertyOne<Term>)getProperty("term")).getValue();
  }
}
