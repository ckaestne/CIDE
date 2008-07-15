package tmp.generated_simple;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Member2 extends Member {
  public Member2(Field field, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Field>("field", field)
    }, firstToken, lastToken);
  }
  public Member2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new Member2(cloneProperties(),firstToken,lastToken);
  }
  public Field getField() {
    return ((PropertyOne<Field>)getProperty("field")).getValue();
  }
}
