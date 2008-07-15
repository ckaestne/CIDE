package tmp.generated_c;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class DirectAbstractDeclaratorP11 extends DirectAbstractDeclaratorP1 {
  public DirectAbstractDeclaratorP11(AbstractDeclarator abstractDeclarator, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<AbstractDeclarator>("abstractDeclarator", abstractDeclarator)
    }, firstToken, lastToken);
  }
  public DirectAbstractDeclaratorP11(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new DirectAbstractDeclaratorP11(cloneProperties(),firstToken,lastToken);
  }
  public AbstractDeclarator getAbstractDeclarator() {
    return ((PropertyOne<AbstractDeclarator>)getProperty("abstractDeclarator")).getValue();
  }
}
