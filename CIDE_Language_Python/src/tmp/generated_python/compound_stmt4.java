package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class compound_stmt4 extends compound_stmt {
  public compound_stmt4(try_stmt try_stmt, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<try_stmt>("try_stmt", try_stmt)
    }, firstToken, lastToken);
  }
  public compound_stmt4(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new compound_stmt4(cloneProperties(),firstToken,lastToken);
  }
  public try_stmt getTry_stmt() {
    return ((PropertyOne<try_stmt>)getProperty("try_stmt")).getValue();
  }
}
