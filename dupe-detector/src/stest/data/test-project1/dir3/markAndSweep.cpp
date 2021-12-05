void markAndSweep()
{
 // mark
 for (all pointers P on the run-time stack or
   in the static data area )
  {
    mark *P;
  }

 //sweep
 for (all objects *P on the heap)
   {
     if *P is not marked then
        delete P
     else
        unmark *P
   }
}

template <class T>
void mark(T* p)
{
  if *p is not already marked
    {
      mark *p;
      for (all pointers q inside *p)
        {
          mark *q;
        }
     }
}
