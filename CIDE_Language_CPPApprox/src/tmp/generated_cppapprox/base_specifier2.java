package tmp.generated_cppapprox;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class base_specifier2 extends base_specifier {
  public base_specifier2(access_specifier access_specifier, ASTStringNode identifier1, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrOne<access_specifier>("access_specifier", access_specifier),
      new PropertyOne<ASTStringNode>("identifier1", identifier1)
    }, firstToken, lastToken);
  }
  public base_specifier2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new base_specifier2(cloneProperties(),firstToken,lastToken);
  }
  public access_specifier getAccess_specifier() {
    return ((PropertyZeroOrOne<access_specifier>)getProperty("access_specifier")).getValue();
  }
  public ASTStringNode getIdentifier1() {
    return ((PropertyOne<ASTStringNode>)getProperty("identifier1")).getValue();
  }
}
