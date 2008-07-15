package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class goto_statement extends GenASTNode {
  public goto_statement(goto_statementEnd goto_statementEnd, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<goto_statementEnd>("goto_statementEnd", goto_statementEnd)
    }, firstToken, lastToken);
  }
  public goto_statement(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new goto_statement(cloneProperties(),firstToken,lastToken);
  }
  public goto_statementEnd getGoto_statementEnd() {
    return ((PropertyOne<goto_statementEnd>)getProperty("goto_statementEnd")).getValue();
  }
}
