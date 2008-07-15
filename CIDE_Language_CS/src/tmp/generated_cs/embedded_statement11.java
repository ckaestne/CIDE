package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class embedded_statement11 extends embedded_statement {
  public embedded_statement11(unsafe_statement unsafe_statement, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<unsafe_statement>("unsafe_statement", unsafe_statement)
    }, firstToken, lastToken);
  }
  public embedded_statement11(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new embedded_statement11(cloneProperties(),firstToken,lastToken);
  }
  public unsafe_statement getUnsafe_statement() {
    return ((PropertyOne<unsafe_statement>)getProperty("unsafe_statement")).getValue();
  }
}
