package tmp.generated_fj;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class MinusExpressionRest extends GenASTNode {
  public MinusExpressionRest(Term term, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Term>("term", term)
    }, firstToken, lastToken);
  }
  public MinusExpressionRest(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new MinusExpressionRest(cloneProperties(),firstToken,lastToken);
  }
  public Term getTerm() {
    return ((PropertyOne<Term>)getProperty("term")).getValue();
  }
}
