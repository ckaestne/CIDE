package tmp.generated_c;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class ParameterDeclarationInternal2 extends ParameterDeclarationInternal {
  public ParameterDeclarationInternal2(AbstractDeclarator abstractDeclarator, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrOne<AbstractDeclarator>("abstractDeclarator", abstractDeclarator)
    }, firstToken, lastToken);
  }
  public ParameterDeclarationInternal2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new ParameterDeclarationInternal2(cloneProperties(),firstToken,lastToken);
  }
  public AbstractDeclarator getAbstractDeclarator() {
    return ((PropertyZeroOrOne<AbstractDeclarator>)getProperty("abstractDeclarator")).getValue();
  }
}
