package tmp.generated_cppapprox;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class class_head1 extends class_head {
  public class_head1(class_key class_key, ASTStringNode identifier, base_specification base_specification, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<class_key>("class_key", class_key),
      new PropertyOne<ASTStringNode>("identifier", identifier),
      new PropertyZeroOrOne<base_specification>("base_specification", base_specification)
    }, firstToken, lastToken);
  }
  public class_head1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new class_head1(cloneProperties(),firstToken,lastToken);
  }
  public class_key getClass_key() {
    return ((PropertyOne<class_key>)getProperty("class_key")).getValue();
  }
  public ASTStringNode getIdentifier() {
    return ((PropertyOne<ASTStringNode>)getProperty("identifier")).getValue();
  }
  public base_specification getBase_specification() {
    return ((PropertyZeroOrOne<base_specification>)getProperty("base_specification")).getValue();
  }
}
