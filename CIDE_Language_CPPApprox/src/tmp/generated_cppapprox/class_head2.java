package tmp.generated_cppapprox;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class class_head2 extends class_head {
  public class_head2(class_key class_key1, base_specifier base_specifier, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<class_key>("class_key1", class_key1),
      new PropertyZeroOrOne<base_specifier>("base_specifier", base_specifier)
    }, firstToken, lastToken);
  }
  public class_head2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new class_head2(cloneProperties(),firstToken,lastToken);
  }
  public class_key getClass_key1() {
    return ((PropertyOne<class_key>)getProperty("class_key1")).getValue();
  }
  public base_specifier getBase_specifier() {
    return ((PropertyZeroOrOne<base_specifier>)getProperty("base_specifier")).getValue();
  }
}
