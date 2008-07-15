package tmp.generated_cppapprox;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class base_specifier3 extends base_specifier {
  public base_specifier3(access_specifier access_specifier1, ASTTextNode text97, ASTStringNode identifier2, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<access_specifier>("access_specifier1", access_specifier1),
      new PropertyZeroOrOne<ASTTextNode>("text97", text97),
      new PropertyOne<ASTStringNode>("identifier2", identifier2)
    }, firstToken, lastToken);
  }
  public base_specifier3(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new base_specifier3(cloneProperties(),firstToken,lastToken);
  }
  public access_specifier getAccess_specifier1() {
    return ((PropertyOne<access_specifier>)getProperty("access_specifier1")).getValue();
  }
  public ASTTextNode getText97() {
    return ((PropertyZeroOrOne<ASTTextNode>)getProperty("text97")).getValue();
  }
  public ASTStringNode getIdentifier2() {
    return ((PropertyOne<ASTStringNode>)getProperty("identifier2")).getValue();
  }
}
