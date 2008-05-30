package tmp.generated_dtd;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class SeqOrChoice2 extends SeqOrChoice {
  public SeqOrChoice2(Seq seq, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Seq>("seq", seq)
    }, firstToken, lastToken);
  }
  public SeqOrChoice2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new SeqOrChoice2(cloneProperties(),firstToken,lastToken);
  }
  public Seq getSeq() {
    return ((PropertyOne<Seq>)getProperty("seq")).getValue();
  }
}
